package com.sweetmay.advancedcryptoindicators2.model.entity.coin

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GeneralInfoCoinDb(@PrimaryKey val id: String, val name: String)