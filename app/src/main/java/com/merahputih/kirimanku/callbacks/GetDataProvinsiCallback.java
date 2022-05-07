package com.merahputih.kirimanku.callbacks;

import com.merahputih.kirimanku.provinsi_json_output_classes.Rajaongkir;

public interface GetDataProvinsiCallback {
    void onSuccess(Rajaongkir response);
    void onFailed(Exception e);
}
