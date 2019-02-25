package com.sunny.gitpulls.communication;


public interface OnHttpConnListener {
    int ERROR_OCC=101;
    int STREAM_EMPTY=102;
    int MALFORMED_URL=103;
    int IO_EXC=104;

    public void onUpdate(String msg,int mesCode);
}
