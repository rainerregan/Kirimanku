package com.merahputih.kirimanku.callbacks;

import com.merahputih.kirimanku.waybill_json_output_classes.Rajaongkir;

public interface GetDataLacakCallback {
    void onSuccess(Rajaongkir response);
    void onFailed(Exception e);
}
