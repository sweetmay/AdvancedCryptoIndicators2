package com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CoinDb(@PrimaryKey val id: String, val name: String)