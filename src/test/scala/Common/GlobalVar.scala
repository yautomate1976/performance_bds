package Common

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

object GlobalVar {

  val flexFaringVendor = Map(
            "Nextjet AB" -> "2N",
            "Air Inuit Ltd/Ltee" -> "3H",
            "Canadian North Inc" -> "5T",
            "Air Alsie" -> "6I",
            "Bradley Air Services" -> "7F",
            "Pacific Coastal Airlines" -> "8P",
            "Central Mountain Air Ltd" -> "9M",
            "Jet Airways Limited" -> "9W",
            "Air Canada" -> "AC",
            "Air India Limited" -> "AI",
            "Finnair Oyj" -> "AY",
            "Bismillah Airlines" -> "BH",
            "Norwegian Air International" -> "D8",
            "Danish Air Transport" -> "DX",
            "Norwegian Air Shuttle" -> "DY",
            "Jetstar Airways Pty" -> "JQ",
            "Bearskin Lake air services" -> "JV",
            "Calm Air International" -> "MO",
            "Air New Zealand" -> "NZ",
            "Privilege Style" -> "P6",
            "Provincial Airlines" -> "PB",
            "Porter Airlines Inc" -> "PD",
            "Qantas Airways" -> "QF",
            "Jet Lite Limited" -> "S2",
            "Scandinavian Airlines System" -> "SK",
            "Braathens Regional Aviation" -> "TF",
            "Capiteq Limited dba Air North" -> "TL",
            "Tata Sia Airlines" -> "UK",
            "Virgin Australia International" -> "VA",
            "Wideroe Flyveselskap" -> "WF",
            "Sunwing Airlines Inc" -> "WG",
            "WestJet" -> "WS",
            "Air Creebec Inc" -> "YN",
            "Regional Express Pty" -> "ZL"
  )

  val userCount = Integer.getInteger("userCount", 25)
  val rampUpSeconds = Integer.getInteger("rampUpSeconds", 30)
  val loopRounds = Integer.getInteger("loopRounds", 3).toInt
  val baseUrl: String = "http://bdsqa.hushaboom.concurasp.com"
  val httpConf: HttpProtocolBuilder = http.baseURL(baseUrl)
  val bdsBrandPath : String = "/bds/api/v1/brands"
  val bdsMappingPath : String = "/bds/api/v1/manage/mapping"

}