package com.example.bt1.bth2

import java.io.Serializable

class JobModel(
    var id: Int?,
    var ten: String?,
    var noiDung: String?,
    var ngay: String?,
    var tinhTrang: String?,
    var congTac: String?
): Serializable {
}