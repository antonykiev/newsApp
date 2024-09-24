package com.petproject.server

import com.petproject.server.api.RemoteApi

class RemoteApiImpl(
    private val remoteApi: RemoteApi
): RemoteApi by remoteApi