package com.tcs.mvvmboilerplate.data.model.pokemon

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)