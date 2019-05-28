package com.tunhuofeng.experience.Util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;


import com.tunhuofeng.experience.MyApplication;
import com.tunhuofeng.experience.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UpdateVersionService extends Service {
	private static final int NOTIFY_ID = 0;
	private int progress;
	private NotificationManager mNotificationManager;
	private boolean canceled;
	// 返回的安装包url
	private String apkUrl = "";
	/* 下载包安装路径 */
	private static final String savePath = Constants.CACHE_DIR_VERSION;

	private static final String saveFileName = savePath + "/ZGBAppUpdate.apk";
	private DownloadBinder binder;
	private MyApplication app;
	private boolean serviceIsDestroy = false;

	private Context mContext = this;
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
				case 0:

					// 下载完毕
					// 取消通知
					mNotificationManager.cancel(NOTIFY_ID);
					installApk();
					break;
				case 2:

					// 这里是用户界面手动取消，所以会经过activity的onDestroy();方法
					// 取消通知
					mNotificationManager.cancel(NOTIFY_ID);
					break;
				case 1:
					int rate = msg.arg1;

					if (rate < 100) {
						RemoteViews contentview = mNotification.contentView;
						contentview.setTextViewText(R.id.tv_progress, rate + "%");
						contentview.setProgressBar(R.id.progressbar, 100, rate, false);
					} else {
//						System.out.println("下载完毕!!!!!!!!!!!");
//						// 下载完毕后变换通知形式
//						mNotification.flags = Notification.FLAG_AUTO_CANCEL;
//						mNotification.contentView = null;
//						Intent intent = new Intent(mContext, NotificationUpdateActivity.class);
//						// 告知已完成
//						intent.putExtra("completed", "yes");
//						// 更新参数,注意flags要使用FLAG_UPDATE_CURRENT
//						PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0, intent,
//								PendingIntent.FLAG_UPDATE_CURRENT);
//						mNotification.setLatestEventInfo(mContext, "下载完成", "文件已下载完毕", contentIntent);
						//
						serviceIsDestroy = true;
						stopSelf();// 停掉服务自身
					}
					mNotificationManager.notify(NOTIFY_ID, mNotification);
					break;
			}
		}
	};

	//
	// @Override
	// public int onStartCommand(Intent intent, int flags, int startId) {
	// return START_STICKY;
	// }

	@Override
	public IBinder onBind(Intent intent) {
		apkUrl=intent.getStringExtra("UpdataUrl");
		// 2017/5/3 测试下载连接
		// http://m.gdown.baidu.com/6712476209b26e7c97ed53cb34bc6e00b81e571db908fe6adcfb34fe292e4839a43dac81dff90fea4c06cfb4f0b701d2ec8e394459a281c5e9dd292df6d262cfb14fb071baf27116e3db14ef2602fc93c314701bdd27b79b141cf0cf4368df61d120d74e2a5659a68214d534f59dc97c753bff2991cf39e88cd505a4b26518abd272c46ced17d697586bac0f192c7eab0dc7ffa4ac1b8600aaad274eca3255b1
		return binder;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		// 假如被销毁了，无论如何都默认取消了。app.setDownload(false);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}

	@Override
	public void onRebind(Intent intent) {
		super.onRebind(intent);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		binder = new DownloadBinder();
		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		//setForeground(true);// 这个不确定是否有作用
		app = (MyApplication) getApplication();


	}

	public class DownloadBinder extends Binder {
		public void start() {
			if (downLoadThread == null || !downLoadThread.isAlive()) {
				progress = 0;
				setUpNotification();
				new Thread() {
					public void run() {
						// 下载
						startDownload();
					}
				}.start();
			}
		}

		public void cancel() {
			canceled = true;
		}

		public int getProgress() {
			return progress;
		}

		public boolean isCanceled() {
			return canceled;
		}

		public boolean serviceIsDestroy() {
			return serviceIsDestroy;
		}

		public void cancelNotification() {
			mHandler.sendEmptyMessage(2);
		}
	}

	private void startDownload() {
		canceled = false;
		downloadApk();
	}

	//
	Notification mNotification;

	// 通知栏
	/**
	 * 创建通知
	 */
	private void setUpNotification() {
		int icon = R.mipmap.ic_launcher;
		CharSequence tickerText = "开始下载";
		long when = System.currentTimeMillis();
		//mNotification = new Notification(icon, tickerText, when);
		// 放置在"正在运行"栏目中


//		RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.update_version_notification);
//		contentView.setTextViewText(R.id.name, "掌柜帮手机版 正在下载...");
		// 指定个性化视图

		Bitmap bmp=BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
		mNotification = new Notification.Builder(this)
				.setLargeIcon(bmp)
				.setTicker(tickerText)
				.setContentText("掌柜帮手机版 正在下载..."+progress+"%")
				.setWhen(when)
				.setProgress(100,progress,false)
				.build();
		//mNotification.flags |= Notification.FLAG_AUTO_CANCEL; // FL
		mNotification.flags = Notification.FLAG_ONGOING_EVENT;
		mNotificationManager.notify(2,mNotification);

	}

	//
	/**
	 * 下载apk
	 *
	 * @param url
	 */
	private Thread downLoadThread;

	private void downloadApk() {
		downLoadThread = new Thread(mdownApkRunnable);
		downLoadThread.start();
	}

	/**
	 * 安装apk
	 */
	private void installApk() {
		File apkfile = new File(saveFileName);
		if (!apkfile.exists()) {
			return;
		}
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.setDataAndType(Uri.fromFile(apkfile), "application/vnd.android.package-archive");
		mContext.startActivity(i);
		//callback.OnBackResult("finish");

	}

	private int lastRate = 0;
	private Runnable mdownApkRunnable = new Runnable() {
		@Override
		public void run() {
			try {
				URL url = new URL(apkUrl);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.connect();
				int length = conn.getContentLength();
				InputStream is = conn.getInputStream();
				File file = new File(savePath);
				if (!file.exists()) {
					file.mkdirs();
				}
				String apkFile = saveFileName;
				File ApkFile = new File(apkFile);
				FileOutputStream fos = new FileOutputStream(ApkFile);
				int count = 0;
				byte buf[] = new byte[1024];
				do {
					int numread = is.read(buf);
					count += numread;
					progress = (int) (((float) count / length) * 100);
					// 更新进度
					Message msg = mHandler.obtainMessage();
					msg.what = 1;
					msg.arg1 = progress;
					if (progress >= lastRate + 1) {
						mHandler.sendMessage(msg);
						lastRate = progress;
					}
					if (numread <= 0) {
						// 下载完成通知安装
						mHandler.sendEmptyMessage(0);
						// 下载完了，cancelled也要设置
						canceled = true;
						break;
					}
					fos.write(buf, 0, numread);
				} while (!canceled);// 点击取消就停止下载.

				fos.close();
				is.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	};

	private void sendNotification(){

		NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"随便一个id");
		builder.setSmallIcon(R.mipmap.ic_launcher);
		builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
		//禁止用户点击删除按钮删除
		builder.setAutoCancel(false);


		builder.setContentTitle("掌柜帮手机版 正在下载..."+progress+"%");
		builder.setProgress(100,progress,false);
		//builder.setContentInfo(progress+"%");
		//禁止滑动删除
		builder.setOngoing(true);
		builder.setShowWhen(false);
		Intent intent = new Intent(this,UpdateVersionService.class);
		intent.putExtra("command",1);
		Notification notification = builder.build();
		mNotificationManager.notify(2,notification);

	}

}
