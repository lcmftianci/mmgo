package com.lcmf.mmgo.threelife;

import android.util.Log;

public class VulThread extends Thread {
    private static String TAG = "VulThread";

    private int m_vulx;
    private int m_vuly;

    public VulThread(){
        m_vulx = 0;
        m_vuly = 0;
    }

    @Override
    public void run() {
        super.run();
        while (true){
            try{
                Thread.sleep(200);
                Log.d(TAG, "x:" + m_vulx + " y:" + m_vuly);
                if(m_vulx > 0)
                    m_vulx--;

                if(m_vulx < 0)
                    m_vulx++;

                if(m_vuly > 0)
                    m_vuly--;

                if(m_vuly < 0)
                    m_vuly++;
            }catch (Exception e){
                Log.d(TAG, "sleep error");
            }
        }
    }

    public void setVulX(int vulx){
        m_vulx = vulx;
    }
    public int getVulX(){
        return m_vulx;
    }

    public void setVulY(int vuly){
        m_vuly = vuly;
    }
    public int getVulY(){
        return m_vuly;
    }

}
