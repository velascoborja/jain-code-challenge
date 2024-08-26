package com.akansha.digitalsurgery.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.akansha.digitalsurgery.MainCoroutineRule
import com.akansha.digitalsurgery.getOrAwaitValueTest
import com.akansha.digitalsurgery.model.ProcedureDetailCard
import com.akansha.digitalsurgery.model.ProcedureDetailResult
import com.akansha.digitalsurgery.model.ProcedureItem
import com.akansha.digitalsurgery.model.ProcedureListResult
import com.akansha.digitalsurgery.networking.model.Image
import com.akansha.digitalsurgery.networking.model.Phase
import com.akansha.digitalsurgery.networking.model.Procedure
import com.akansha.digitalsurgery.networking.model.ProcedureDetail
import com.akansha.digitalsurgery.repository.MockProcedureRepository
import com.akansha.digitalsurgery.repository.mapper.map
import com.akansha.digitalsurgery.screens.viewmodel.ProcedureViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProcedureViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val mockRepository = MockProcedureRepository()
    private lateinit var viewModel: ProcedureViewModel

    @Before
    fun setup() {
        viewModel = ProcedureViewModel(mockRepository)
    }

    @Test
    fun `GIVEN the repository returns successful data WHEN viewModel method is called THEN return the expected list`() =
        runTest {
            val mockProcedureList = listOf(
                Procedure(
                    id = "ProcedureId",
                    image = Image(uuid = "Icon1", url = "mockUrl", version = 1),
                    title = "Procedure1",
                    phases = listOf("Phase1")
                )
            )
            val repositoryResult = ProcedureListResult.Success(procedures = mockProcedureList.map())
            mockRepository.procedureListResult = repositoryResult
            val expectedResult = repositoryResult.procedures

            viewModel.getProcedures()
            advanceUntilIdle()
            val result = viewModel.procedureLiveData.getOrAwaitValueTest()

            assertEquals(result, expectedResult)
        }

    @Test
    fun `GIVEN the repository returns failure WHEN viewmodel method is called THEN return an empty list`() =
        runTest {
            mockRepository.procedureListResult = ProcedureListResult.Failure
            val expectedResult = emptyList<ProcedureItem>()

            viewModel.getProcedures()
            advanceUntilIdle()
            val result = viewModel.procedureLiveData.getOrAwaitValueTest()

            assertEquals(result, expectedResult)
        }

    @Test
    fun `GIVEN the repository returns successful data for details WHEN viewModel getProcedureDetails method is called THEN return the expected detail object`() =
        runTest {
            val mockProcedureDetail = ProcedureDetail(
                id = "procedureId",
                title = "title",
                card = Image(uuid = "Icon1", url = "mockUrl", version = 1),
                duration = 120,
                creationDate = "2015-04-14T10:00:51.940581",
                phases = listOf(
                    Phase(
                        id = "id",
                        name = "phaseName",
                        image = Image(uuid = "PhaseIcon1", url = "mockUrl", version = 1),
                    )
                )
            )
            val repositoryResult =
                ProcedureDetailResult.Success(details = mockProcedureDetail.map())
            mockRepository.procedureDetailResult = repositoryResult
            val expectedResult = repositoryResult.details

            viewModel.getProcedureDetails("procedureId")
            advanceUntilIdle()
            val result = viewModel.procedureDetailLiveData.getOrAwaitValueTest()

            assertEquals(result, expectedResult)
        }

    @Test
    fun `GIVEN the repository returns a failure for details WHEN viewmodel getProcedureDetails method is called THEN return an empty object`() =
        runTest {
            mockRepository.procedureDetailResult = ProcedureDetailResult.Failure
            val expectedResult = ProcedureDetailCard()

            viewModel.getProcedureDetails("procedureId")
            advanceUntilIdle()
            val result = viewModel.procedureDetailLiveData.getOrAwaitValueTest()

            assertEquals(result, expectedResult)
        }
}