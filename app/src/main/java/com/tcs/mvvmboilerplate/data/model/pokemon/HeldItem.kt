package com.tcs.mvvmboilerplate.data.model.pokemon

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)