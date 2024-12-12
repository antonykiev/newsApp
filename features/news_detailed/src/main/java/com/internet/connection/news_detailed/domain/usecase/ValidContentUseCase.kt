package com.internet.connection.news_detailed.domain.usecase

import javax.inject.Inject

class ValidContentUseCase @Inject constructor() {

    operator fun invoke(
        content: String,
    ): String {
        return content.split("…").first()
    }
}