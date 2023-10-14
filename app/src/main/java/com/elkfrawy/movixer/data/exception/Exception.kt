package com.elkfrawy.movixer.data.exception

class ClientException: Throwable("Client Exception")
class ServerException: Throwable("Server Error")
class DataNotAvailableException: Throwable("Empty Data")