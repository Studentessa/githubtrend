package com.martinez.data

import com.martinez.data.domain.ProjectsErrorCode
import com.martinez.data.mapper.ProjectPresentationMapper

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertSame

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class ProjectMapperTest {
    private lateinit var cut: ProjectPresentationMapper

    companion object {
        private val DATA_MODEL = Fixtures.ProjectList.Data.model[0]
    }
    @Before
    fun setUp() {
        cut = ProjectPresentationMapper()
    }

    @Test
    fun `project data model is correctly mapped to domain model`() {
        val domainModel = cut.toDomainModel(DATA_MODEL)

        domainModel.run {
            assertEquals(author, DATA_MODEL.author)
            assertEquals(avatar, DATA_MODEL.avatar)
            assertEquals(url, DATA_MODEL.url)
            assertEquals(description, DATA_MODEL.description)
            assertEquals(stars, DATA_MODEL.stars)
            assertEquals(forks, DATA_MODEL.forks)
            assertEquals(language, DATA_MODEL.language)
        }
    }

    @Test
    fun `IOException is mapped to  ServerNotReachable error code`() {
        val ioException = IOException()

        val domainModel = cut.toErrorDomainModel(ioException)

        with(domainModel) {
            assertEquals(message, ioException.message)
            assertSame(errorCode, ProjectsErrorCode.ServerNotReachable)
        }
    }

    @Test
    fun `String errror message is correctly mapped to ResponseErrorModel error domain model`() {
        val responseErrorModel = "unknown error code"

        val domainModel = cut.toErrorDomainModel(responseErrorModel)

        with(domainModel) {
            assertEquals(message,responseErrorModel )
            assertEquals(errorCode, ProjectsErrorCode.Unknown)
        }
    }


}