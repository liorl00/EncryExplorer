package org.encryfoundation.explorer.db.tables

object HeadersTable extends Table {
  val name: String = "headers"
  val fields: Seq[String] = Seq(
    "id",
    "parent_id",
    "version",
    "height",
    "ad_proofs_root",
    "state_root",
    "transactions_root",
    "ts",
    "difficulty",
    "block_size",
    "equihash_solution",
    "ad_proofs",
    "tx_qty",
    "miner_address",
    "miner_reward",
    "fees_total",
    "txs_size",
    "best_chain"
  )
  val fieldsString: String = fields.mkString("(", ", ", ")")
}