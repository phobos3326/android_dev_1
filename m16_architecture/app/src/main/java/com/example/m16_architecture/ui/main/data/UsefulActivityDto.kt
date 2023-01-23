package com.example.m16_architecture.ui.main.data

import com.example.m16_architecture.ui.main.entity.UsefulActivity
import dagger.Component

@Component
class UsefulActivityDto(
    override val activity: String,
    override val type: String,
    override val participants: Int,
    override val link: String,
    override val key: Int,
    override val accessibility: Int,
) : UsefulActivity