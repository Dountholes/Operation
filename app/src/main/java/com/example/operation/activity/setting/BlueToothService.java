package com.example.operation.activity.setting;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class BlueToothService extends Service {
    private BlueToothBinder blueToothBinder;
    /*
      **获取本地蓝牙适配器，即手机蓝牙设备
       */
    private BluetoothAdapter _bluetooth ;
    /*
    **单片机蓝牙模块
     */
    private BluetoothDevice _device;
    /*
    **单片机蓝牙模块设备地址
     */
    private String address=null;
    /*
    **单片机蓝牙模块设备地址常数，防止连接其它设备
     */
    private String ADDRESS="98:D3:33:80:C0:E6";
    /*
    **蓝牙通信的socket
     */
    BluetoothSocket _socket = null;
    /*
    ** SPP服务UIID号,串口服务UIID
     */
    private final static String MY_UUID="00001101-0000-1000-8000-00805F9B34FB";//"00001101-0000-1000-8000-00805F9B34FB";   //SPP服务UUID号
    /*
    **用来标记广播注册状态
     */
  //  private boolean FLAG=false;
    //private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
       // @Override
       // public void onReceive(Context context, Intent intent) {
          //  String action = intent.getAction();
          //  if(BluetoothDevice.ACTION_FOUND.equals(action))
           // {
           //     _device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);//这个得到的好像不是BluetoothDevice，尝试以下
           // }
           // if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
           //     /*
           //     **因为我们这只有一个设备，所以就直接获取搜索结束后获取到的设备，当然这里可能存在隐患，要在其它地方避免连接到其它设备
           //     **我想直接连接蓝牙模块后记录物理地址，用这个进行设备判断
           //      */
//
      //  if(_device==null)
      //  {
      //      /*
      //      **未搜索到蓝牙设备进行提示
      //       */
      //      Toast.makeText(BlueToothService.this,"未搜索到蓝牙设备！",Toast.LENGTH_SHORT).show();//这里可能要重新启动服务了,先放一放
      //  }else
      //  {
      //      /*
      //      **获取地址
      //       */
      //      address=_device.getAddress();//已经能确定我顺利获取到了地址
      //      System.out.println("1蓝牙大哥的地址"+address);
      //  }
      //
      //
      //

    public BlueToothService() {
    }
@Override
public int onStartCommand(Intent intent,int flags,int startId)
{
    blueToothBinder=new BlueToothBinder();
    _bluetooth= BluetoothAdapter.getDefaultAdapter();
    /*
    **添加蓝牙连接逻辑，防止每次绑定服务时都要连接
     */
    if (_bluetooth == null) {
        Toast.makeText(this, "蓝牙不可用！", Toast.LENGTH_LONG).show();
    }
    /*
    **实时判断蓝牙是否打开
    */
    new Thread(new Runnable(){
    @Override
        public void run() {
            if (!_bluetooth.isEnabled()) {
                _bluetooth.enable();
            }
        }
    }).start();
    /*
    **查找单片机蓝牙模块
     */
   // doDiscovery();
     /*
    **注册广播接收蓝牙连接的反馈信息,此处为搜索结束
    */
   // IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
    //this.registerReceiver(mReceiver, filter);
    //filter=new IntentFilter(BluetoothDevice.ACTION_FOUND);
    //this.registerReceiver(mReceiver,filter);
    /*
    **标记已经注册广播
     */
   // FLAG=true;
    /*
    **与单片机进行连接
     */
    return super.onStartCommand(intent,flags,startId);
}
    @Override
    public IBinder onBind(Intent intent) {
        return blueToothBinder;//向客户端返回"通信纽带“
    }
    /*
    **寻找单片机蓝牙模块
     */
   // public void doDiscovery()
   // {
        /*
        **如果正在查找就取消查找，进行新的连接
       */
       // if (_bluetooth.isDiscovering()) {
      //      _bluetooth.cancelDiscovery();
       // }
       // _bluetooth.startDiscovery();
       // if(_bluetooth==null)
       // {
       //     System.out.println("蓝牙未打开");
       // }
      //  Toast.makeText(this, "正在搜索", Toast.LENGTH_SHORT).show();
//
   // }
    /*
    **利用蓝牙连接单片机逻辑
     */
    //连接失败重新开始服务的问题还没有解决，先放着，整体过程完成后再解决
    public void connect(String Address)
    {
        /*
        **每次连接前检测蓝牙是否打开，排除蓝牙未打开引起的空指针异常,放在这里还是因为线程的问题会产生空指针，
        * 因为检测请求的过程和接下来的代码运行是并行的，所以这里蓝牙可能还没打开，后面就已经在蓝牙必须打开才能正常
        * 运行的情况下，运行了，所以产生异常
         */
        _device=_bluetooth.getRemoteDevice(Address);
        try{
            /*
            **获取socket
             */
        _socket = _device.createRfcommSocketToServiceRecord(UUID.fromString(MY_UUID));
        }catch (IOException ex)
        {
            Toast.makeText(this, "1连接失败！", Toast.LENGTH_SHORT).show();
            }
        /*
        **socket连接
         */
        try{
            _socket.connect();
            Toast.makeText(this, "连接" + _device.getName() + "成功！", Toast.LENGTH_SHORT).show();
        }catch(IOException ex)
        {
            try {
                System.out.println("在这里抛出异常1");
                Toast.makeText(this, "连接失败！", Toast.LENGTH_SHORT).show();
                _socket.close();
                _socket = null;
            } catch (IOException ee) {
                System.out.println("在这里抛出异常2");
                Toast.makeText(this, "连接失败！", Toast.LENGTH_SHORT).show();
            }
            ex.printStackTrace();
        }
    }
    /*
    **用于和客户端通信
     */
    class BlueToothBinder extends Binder{
        /*
        **将connect方法也做一个封装
         */
        public void connectBluetooth()
        {
            //if(_bluetooth.isDiscovering())
              //  _bluetooth.cancelDiscovery();//取消搜索
            //if(FLAG)
           // {
           //     BlueToothService.this.unregisterReceiver(mReceiver);
            //    FLAG=false;//细节
            //}
            connect(ADDRESS);
           // System.out.println("3socket在此"+_socket.toString());//空指针
           // if(_socket==null)
            //{
            //    System.out.println("socket为空指针");
            //}
        }
        /*
        **客户端与服务端通信接口，向单片机发送信息
         */
        public boolean sendMessage(String message)
        {
            int i = 0;
            int n = 0;
            try {
                OutputStream os = _socket.getOutputStream();
                byte[] bos =message.getBytes();//输入信息获取点
			/*
			**处理换行
			 */
                for (i = 0; i < bos.length; i++) {
                    if (bos[i] == 0x0a) n++;
                }
                byte[] bos_new = new byte[bos.length + n];
                n = 0;
                for (i = 0; i < bos.length; i++) {
                    if (bos[i] == 0x0a) {
                        bos_new[n] = 0x0d;//“/r"
                        n++;
                        bos_new[n] = 0x0a;//"/n",windows系统,/r/n代表换行
                    } else {
                        bos_new[n] = bos[i];
                    }
                    n++;
                }
                os.write(bos_new);
                /*
                **神来之笔！
                 */
                return true;
            } catch (Exception ex) {//这里如果捕捉IOException就可能捕捉不到异常，虽然我也有些疑惑，天才
                ex.printStackTrace();
                return false;
            }
        }

    }
    @Override
    public void onDestroy()
    {
        if (_socket != null)  //关闭连接socket
            try {
                _socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        if (_bluetooth.isEnabled()) {
            _bluetooth.disable();
            Toast.makeText(BlueToothService.this, "蓝牙已关闭", Toast.LENGTH_SHORT).show();
        }
    }
}
