package controllers

import io.circe.syntax._
import javax.inject.Inject
import models.Transaction
import play.api.libs.circe.Circe
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import services.TransactionsService
import views.html.{getTransactions, getTransactionsList}
import scala.concurrent.ExecutionContext
import scala.util.control.NonFatal

class TransactionsController @Inject()(cc: ControllerComponents, transactionsService: TransactionsService)(implicit ex: ExecutionContext)
  extends AbstractController(cc) with Circe {

  def findOutputApi(id: String): Action[AnyContent] = Action.async {
    transactionsService
      .findOutput(id)
      .map(output => Ok(output.asJson))
      .recover {
        case NonFatal(_) => BadRequest
      }
  }

  def listOutputsByAddressApi(address: String): Action[AnyContent] = Action.async {
    transactionsService
      .listOutputsByAddress(address)
      .map(outputs => Ok(outputs.asJson))
      .recover {
        case NonFatal(_) => BadRequest
      }
  }

  def listUnspentOutputsByAddressApi(address: String): Action[AnyContent] = Action.async {
    transactionsService
      .listOutputsByAddress(address, unspentOnly = true)
      .map(outputs => Ok(outputs.asJson))
      .recover {
        case NonFatal(_) => BadRequest
      }
  }

  def findOutputsByTxIdApi(id: String): Action[AnyContent] = Action.async {
    transactionsService
      .findOutputsByTxId(id)
      .map(outputs => Ok(outputs.asJson))
      .recover {
        case NonFatal(_) => BadRequest
      }
  }

  def findUnspentOutputsByTxIdApi(id: String): Action[AnyContent] = Action.async {
    transactionsService
      .findUnspentOutputsByTxId(id)
      .map(outputs => Ok(outputs.asJson))
      .recover {
        case NonFatal(_) => BadRequest
      }
  }

  def findInputApi(id: String): Action[AnyContent] = Action.async {
    transactionsService
      .findInput(id)
      .map(inputs => Ok(inputs.asJson))
      .recover {
        case NonFatal(_) => BadRequest
      }
  }

  def listInputsByTxIdApi(txId: String): Action[AnyContent] = Action.async {
    transactionsService
      .listInputs(txId)
      .map(inputs => Ok(inputs.asJson))
      .recover {
        case NonFatal(_) => BadRequest
      }
  }

  def findTransactionApi(id: String): Action[AnyContent] = Action.async {
    transactionsService.findTransaction(id).map {
      case Some(transaction) => Ok(transaction.asJson)
      case None => NotFound
    }
  }

  def findTransactionView(id: String): Action[AnyContent] = Action.async {
    transactionsService.findTransaction(id).map {
      case Some(transaction) => Ok(getTransactions(transaction))
      case None => NotFound
    }
  }

  def listByBlockIdApi(blockId: String): Action[AnyContent] = Action.async {
    transactionsService.listTransactionsByBlockId(blockId).map {
      case Nil => NotFound
      case list: List[Transaction] => Ok(list.asJson)
    }
  }

  def listByBlockIdView(blockId: String): Action[AnyContent] = Action.async {
    transactionsService.listTransactionsByBlockId(blockId).map {
      case Nil => NotFound
      case list: List[Transaction] => Ok(getTransactionsList(list))
    }
  }

  def outputsByBlockHeightApi(height: Int): Action[AnyContent] = Action.async {
    transactionsService
      .listOutputsByBlockHeight(height)
      .map(tx => Ok(tx.asJson))
      .recover {
        case NonFatal(_) => BadRequest
      }
  }

  def unspentOutputsByBlockHeightApi(height: Int): Action[AnyContent] = Action.async {
    transactionsService
      .listOutputsByBlockHeightUnspent(height)
      .map(tx => Ok(tx.asJson))
      .recover {
        case NonFatal(_) => BadRequest
      }
  }

  def findOutputByBlockIdApi(id: String): Action[AnyContent] = Action.async {
    transactionsService
      .findOutputByBlockId(id)
      .map(tx => Ok(tx.asJson))
      .recover {
        case NonFatal(_) => BadRequest
      }
  }

  def findUnspentOutputByBlockIdApi(id: String): Action[AnyContent] = Action.async {
    transactionsService
      .findUnspentOutputByBlockId(id)
      .map(tx => Ok(tx.asJson))
      .recover {
        case NonFatal(_) => BadRequest
      }
  }

  def findTransactionByBlockHeightRangeApi(from: Int, to: Int): Action[AnyContent] = Action.async {
    transactionsService
      .findTransactionByBlockHeightRange(from, to)
      .map(tx => Ok(tx.asJson))
      .recover {
        case NonFatal(_) => BadRequest
      }
  }

}