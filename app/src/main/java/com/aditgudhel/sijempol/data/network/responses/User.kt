package com.aditgudhel.sijempol.data.network.responses

data class User(
    val akses: Akses,
    val akses_id: Int,
    val aktif: Int,
    val created_at: String,
    val email: String,
    val email_verified_at: String,
    val last_login_at: String,
    val last_login_ip: String,
    val nama: String,
    val nip_baru: String,
    val nip_lama: String,
    val poli_id: Any,
    val updated_at: String,
    val user_id: Int,
    val username: String,
    val wilayah_kerja_id: Int
)