package com.withintent.network.mapper

interface BaseMapper<Remote, Local> {
    fun map(remote: Remote): Local
}