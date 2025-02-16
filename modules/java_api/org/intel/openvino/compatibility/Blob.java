// Copyright (C) 2020-2023 Intel Corporation
// SPDX-License-Identifier: Apache-2.0

package org.intel.openvino.compatibility;

public class Blob extends IEWrapper {

    protected Blob(long addr) {
        super(addr);
    }

    public Blob(TensorDesc tensorDesc) {
        super(GetBlob(tensorDesc.getNativeObjAddr()));
    }

    public Blob(TensorDesc tensorDesc, byte[] data) {
        super(BlobByte(tensorDesc.getNativeObjAddr(), data));
    }

    public Blob(TensorDesc tensorDesc, float[] data) {
        super(BlobFloat(tensorDesc.getNativeObjAddr(), data));
    }

    public Blob(TensorDesc tensorDesc, int[] data) {
        super(BlobInt(tensorDesc.getNativeObjAddr(), data));
    }

    public Blob(TensorDesc tensorDesc, long[] data) {
        super(BlobLong(tensorDesc.getNativeObjAddr(), data));
    }

    public Blob(TensorDesc tensorDesc, long cArray) {
        super(BlobCArray(tensorDesc.nativeObj, cArray));
    }

    public TensorDesc getTensorDesc() {
        return new TensorDesc(GetTensorDesc(nativeObj));
    }

    public int size() {
        return size(nativeObj);
    }

    public LockedMemory rmap() {
        return new LockedMemory(rmap(nativeObj));
    }

    /*----------------------------------- native methods -----------------------------------*/
    private native long GetTensorDesc(long addr);

    private static native long GetBlob(long tensorDesc);

    private static native long BlobByte(long tensorDesc, byte[] data);

    private static native long BlobFloat(long tensorDesc, float[] data);

    private static native long BlobInt(long tensorDesc, int[] data);

    private static native long BlobLong(long tensorDesc, long[] data);

    private static native long BlobCArray(long tensorDesc, long cArray);

    private static native int size(long addr);

    private static native long rmap(long addr);

    @Override
    protected native void delete(long nativeObj);
}
