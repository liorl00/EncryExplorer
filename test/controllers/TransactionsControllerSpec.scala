package controllers

import models.{Input, Output, Transaction}
import org.mockito.Mockito._
import org.scalatestplus.play.PlaySpec
import org.mockito.ArgumentMatchers.{eq => eq_}
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.{FakeRequest, Injecting}
import play.api.test.Helpers._
import services.TransactionsService
import org.scalatest.Matchers._
import org.scalatest.mockito.MockitoSugar
import play.api.mvc.Result

import scala.concurrent.{ExecutionContext, Future}

class TransactionsControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting with MockitoSugar {

  "TransactionsController#findOutput" should {
    "find output by it's id if exists" in new TransactionControllerSpecWiring {
      when(transactionsServiceMock.findOutput(sampleOutputId)).thenReturn(Future.successful(Some(sampleOutput)))
      val result: Future[Result] = controller.findOutput(sampleOutputId).apply(FakeRequest())
      verify(transactionsServiceMock).findOutput(eq_(sampleOutputId))
      status(result) shouldBe OK
    }
  }

  "TransactionsController#listOutputsByAddress" should {
    "return all outputs w given address" in new TransactionControllerSpecWiring {
      when(transactionsServiceMock.listOutputsByAddress(sampleAddress, false)).thenReturn(Future.successful(List(sampleOutput)))
      val result: Future[Result] = controller.listOutputsByAddress(sampleAddress).apply(FakeRequest())
      verify(transactionsServiceMock).listOutputsByAddress(eq_(sampleAddress), eq_(false))
      status(result) shouldBe OK
    }
  }

  "TransactionsController#listUnspentOutputsByAddress" should {
    "return all unspent outputs w given address" in new TransactionControllerSpecWiring {
      when(transactionsServiceMock.listOutputsByAddress(sampleAddress, true)).thenReturn(Future.successful(List(sampleOutput)))
      val result: Future[Result] = controller.listUnspentOutputsByAddress(sampleAddress).apply(FakeRequest())
      verify(transactionsServiceMock).listOutputsByAddress(eq_(sampleAddress), eq_(true))
      status(result) shouldBe OK
    }
  }

  "TransactionsController#findOutputsByTxId" should {
    "return all outputs w given transaction id" in new TransactionControllerSpecWiring {
      when(transactionsServiceMock.findOutputsByTxId(sampleTxId)).thenReturn(Future.successful(List(sampleOutput)))
      val result: Future[Result] = controller.findOutputsByTxId(sampleTxId).apply(FakeRequest())
      verify(transactionsServiceMock).findOutputsByTxId(eq_(sampleTxId))
      status(result) shouldBe OK
    }
  }

  "TransactionsController#findUnspentOutputsByTxId" should {
    "return all unspent outputs w given transaction id" in new TransactionControllerSpecWiring {
      when(transactionsServiceMock.findUnspentOutputsByTxId(sampleTxId)).thenReturn(Future.successful(List(sampleOutput)))
      val result: Future[Result] = controller.findUnspentOutputsByTxId(sampleTxId).apply(FakeRequest())
      verify(transactionsServiceMock).findUnspentOutputsByTxId(eq_(sampleTxId))
      status(result) shouldBe OK
    }
  }

  "TransactionsController#findInput" should {
    "find input by it's id if exists" in new TransactionControllerSpecWiring {
      when(transactionsServiceMock.findInput(sampleInputId)).thenReturn(Future.successful(Some(sampleInput)))
      val result: Future[Result] = controller.findInput(sampleInputId).apply(FakeRequest())
      verify(transactionsServiceMock).findInput(eq_(sampleInputId))
      status(result) shouldBe OK
    }
  }

  "TransactionsController#listInputsByTxId" should {
    "return all inputs w given transaction id" in new TransactionControllerSpecWiring {
      when(transactionsServiceMock.listInputs(sampleTxId)).thenReturn(Future.successful(List(sampleInput)))
      val result: Future[Result] = controller.listInputsByTxId(sampleTxId).apply(FakeRequest())
      verify(transactionsServiceMock).listInputs(eq_(sampleTxId))
      status(result) shouldBe OK
    }
  }

  "TransactionsController#findTransaction" should {
    "find transactions by it's id if exists" in new TransactionControllerSpecWiring {
      when(transactionsServiceMock.findTransaction(sampleTxId)).thenReturn(Future.successful(Some(sampleTransaction)))
      val result: Future[Result] = controller.findTransactionApi(sampleTxId).apply(FakeRequest())
      verify(transactionsServiceMock).findTransaction(eq_(sampleTxId))
      status(result) shouldBe OK
    }
  }

  "TransactionsController#listByBlockId" should {
    "find transactions from block with given id" in new TransactionControllerSpecWiring {
      when(transactionsServiceMock.listTransactionsByBlockId(sampleBlockId)).thenReturn(Future.successful(List(sampleTransaction)))
      val result: Future[Result] = controller.listByBlockIdApi(sampleBlockId).apply(FakeRequest())
      verify(transactionsServiceMock).listTransactionsByBlockId(eq_(sampleBlockId))
      status(result) shouldBe OK
    }
  }

  "TransactionsController#outputsByBlockHeight" should {
    "find outputs from blocks on given height" in new TransactionControllerSpecWiring {
      when(transactionsServiceMock.listOutputsByBlockHeight(sampleBlockHeight)).thenReturn(Future.successful(List(sampleOutput)))
      val result: Future[Result] = controller.outputsByBlockHeight(sampleBlockHeight).apply(FakeRequest())
      verify(transactionsServiceMock).listOutputsByBlockHeight(eq_(sampleBlockHeight))
      status(result) shouldBe OK
    }
  }

  "TransactionsController#unspentOutputsByBlockHeight" should {
    "find unspent outputs from blocks on given height" in new TransactionControllerSpecWiring {
      when(transactionsServiceMock.listOutputsByBlockHeightUnspent(sampleBlockHeight)).thenReturn(Future.successful(List(sampleOutput)))
      val result: Future[Result] = controller.unspentOutputsByBlockHeight(sampleBlockHeight).apply(FakeRequest())
      verify(transactionsServiceMock).listOutputsByBlockHeightUnspent(eq_(sampleBlockHeight))
      status(result) shouldBe OK
    }
  }

  "TransactionsController#findOutputByBlockId" should {
    "find outputs from block with given id" in new TransactionControllerSpecWiring {
      when(transactionsServiceMock.findOutputByBlockId(sampleBlockId)).thenReturn(Future.successful(List(sampleOutput)))
      val result: Future[Result] = controller.findOutputByBlockId(sampleBlockId).apply(FakeRequest())
      verify(transactionsServiceMock).findOutputByBlockId(eq_(sampleBlockId))
      status(result) shouldBe OK
    }
  }

  "TransactionsController#findUnspentOutputByBlockId" should {
    "find unspent outputs from block with given id" in new TransactionControllerSpecWiring {
      when(transactionsServiceMock.findUnspentOutputByBlockId(sampleBlockId)).thenReturn(Future.successful(List(sampleOutput)))
      val result: Future[Result] = controller.findUnspentOutputByBlockId(sampleBlockId).apply(FakeRequest())
      verify(transactionsServiceMock).findUnspentOutputByBlockId(eq_(sampleBlockId))
      status(result) shouldBe OK
    }
  }

  "TransactionsController#findTransactionByBlockHeightRange" should {
    "find transactions from blocks in given height range" in new TransactionControllerSpecWiring {
      when(transactionsServiceMock.findTransactionByBlockHeightRange(sampleBlockHeight, sampleBlockHeight + 1)).thenReturn(Future.successful(List(sampleTransaction)))
      val result: Future[Result] = controller.findTransactionByBlockHeightRange(sampleBlockHeight, sampleBlockHeight + 1).apply(FakeRequest())
      verify(transactionsServiceMock).findTransactionByBlockHeightRange(eq_(sampleBlockHeight), eq_(sampleBlockHeight + 1))
      status(result) shouldBe OK
    }
  }

  private trait TransactionControllerSpecWiring {
    val transactionsServiceMock: TransactionsService = mock[TransactionsService]
    val controller: TransactionsController =
      new TransactionsController(stubControllerComponents(), transactionsServiceMock)(inject[ExecutionContext])
    val sampleOutputId: String = "010000691b35d6eaae31a43a2327f58a78f47293a03715821cf83399e4e3a0b0"
    val sampleAddress: String = Array.fill(32)(0).mkString
    val sampleContractHash: String = sampleAddress
    val sampleTxId: String = "0b6df74842f4088b8ba3b6ad7b744cd415769b4a27470f993699c3827a98030c"
    val sampleOutput: Output = Output(
      sampleOutputId,
      sampleTxId,
      100L,
      "487291c237b68dd2ab213be6b5d1174666074a5afab772b600ea14e8285affab",
      sampleContractHash,
      ""
    )
    val sampleInputId: String = "010002d14c0fe540a42af4e96dee14296cff34f7591c8f77d4c3771534ca1131"
    val sampleInput: Input = Input(
      sampleInputId,
      sampleTxId,
      ""
    )
    val sampleBlockId: String = "06de77e451d0c326a0350faa76b31c3421f0677d7da3530501db4cd13103bf3f"
    val sampleTransaction: Transaction = Transaction(
      sampleTxId,
      sampleBlockId,
      true,
      1532037033902L
    )
    val sampleBlockHeight: Int = 200
  }

}