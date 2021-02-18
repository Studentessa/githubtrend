package com.martinez.xapoapp.data

import com.martinez.xapoapp.data.domain.ContributorDomainModel
import com.martinez.xapoapp.data.domain.ProjectDomainModel
import com.martinez.xapoapp.data.model.ContributorModel
import com.martinez.xapoapp.data.model.ProjectModel

class Fixtures {

    object ProjectList {
        object Data {
            val model = listOf(
                    ProjectModel(
                        author = "facebook",
                        name = "athur",
                        avatar = "avatar",
                        url = "https://github.com/xingshaocheng/architect-awesome",
                        stars = 1234,
                        forks = 123,
                        language = "kotlin",
                        description = "App test to Xapo",
                        currentPeriodStars = 78,
                        languageColor = "#FFFF",
                        builtBy = listOf(
                            ContributorModel(
                                username = "Herl",
                                avatar = "",
                                href = "ff"
                            )
                        )
                    ),
                    ProjectModel(
                        author = "Google",
                        name = "athur",
                        avatar = "avatar",
                        url = "https://github.com/xingshaocheng/architect-awesome",
                        stars = 9879,
                        forks = 567,
                        language = "kotlin",
                        description = "App test to Xapo",
                        currentPeriodStars = 56,
                        languageColor = "#FFFF",
                        builtBy = listOf(
                            ContributorModel(
                                username = "Herl",
                                avatar = "",
                                href = "ff"
                            )
                        )
                    )
            )
        }

        object Domain {
            val model =
                listOf(
                    ProjectDomainModel(
                        author = "facebook",
                        name = "athur",
                        avatar = "avatar",
                        url = "https://github.com/xingshaocheng/architect-awesome",
                        stars = 1234,
                        forks = 123,
                        language = "kotlin",
                        description = "App test to Xapo",
                        currentPeriodStars = 78,
                        languageColor = "#FFFF",
                        builtBy = listOf(
                            ContributorDomainModel(
                                username = "Herl",
                                avatar = "",
                                href = "ff"
                            )
                        )
                    ),
                    ProjectDomainModel(
                        author = "Google",
                        name = "athur",
                        avatar = "avatar",
                        url = "https://github.com/xingshaocheng/architect-awesome",
                        stars = 9879,
                        forks = 567,
                        language = "kotlin",
                        description = "App test to Xapo",
                        currentPeriodStars = 56,
                        languageColor = "#FFFF",
                        builtBy = listOf(
                            ContributorDomainModel(
                                username = "Herl",
                                avatar = "",
                                href = "ff"
                            )
                        )
                    )
                )
        }
    }
}