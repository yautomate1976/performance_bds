package Simulations

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import EndPoints.{GetBrandInfoBy, GetMappedBrandInfoBy, POSTMappedBrandInfoBy, corecheck}
import io.gatling.http.protocol.HttpProtocolBuilder
import Common._

/* The  story line
Request per seconds: 25
Request in 10 seconds: 250
Request in 30 seconds: 750
Request in 1 minutes 1500
Request in 10 minutes 15,000
Request in 30 minutes 450,000
Request in 60 minutes 900,000

The performance narration:
inject 5 request per seconds up to 10 seconds
       in 10 seconds, 50 request per second (double the max)
Pause for 5 seconds
in 25 seconds
pause for 5 seconds
inject 5 request at one time
then
inject 1 request at a constant rate of every 10 seconds up to 50 request

 */
  class bdsSuite_Load_Throttle_RPS extends Simulation
  {
    val loopRounds = Integer.getInteger("loopRounds", 3).toInt

    val scn = scenario(this.getClass.getSimpleName)     // scenario definition
      .repeat(loopRounds)
    {
      exec(
        corecheck.GetVendorCode("NZ"),
        pause(1),
        corecheck.GetBrandInfo("NZ"),
        pause(1),
        corecheck.GetBrandInfoByUSRegionQuery("ZCT", "Concur Travel Silver", "US%3BCA"),
        pause(1),
        corecheck.GetBrandInfoByRegionDefinitionQuery("ZCT", "Concur Travel Silver", "US%3BCA"),
        pause(1),
        corecheck.GetBrandNameWithVendorCode( "NZ",  "Class Non-Refundable"),
        pause(1),
        corecheck.GetMappedFullBrandNameByQueryName("ZCT", "Concur Travel Gold"),
        pause(1),
        corecheck.GetMappedBrandNameByVendorCode("NZ"),
        pause(1),
        corecheck.GetMappedBrandNameByBrandName("ZCT", "CONCURTRAVELGOLD1"),
        pause(1),
        pause(1),
        corecheck.POSTBrands(),
        pause(1),
        corecheck.PostBrandInfoByNoRegion(),
        pause(1),
        corecheck.PostBrandInfoBySpecificRegionExactMatch(),
        pause(1),
        corecheck.PostBrandInfoBySpecificRegionReverseMatch(),
        pause(1),
        corecheck.PostBrandInfoBySpecificRegionMatchNotFoundFreeDefinitionNotFound(),
        pause(1),
        corecheck.PostBrandInfoBySpecificRegionNotFoundReturnedFreeDefinition(),
        pause(1),
        corecheck.PostBrandInfoBySpecificRegionMatchNotFoundRegionFree()
      )
    }

    setUp(
        scn.inject(
          constantUsersPerSec(25) during(2 minutes))).throttle(       // inject 30 users per second for 2 minute
          reachRps(50) in (2 minutes),    // inject 25 request per second in 2 minutes and hold for 2 minutes
          holdFor(2 minutes),
          jumpToRps(50),                  // jump to 50 request per second in 2 minutes and hold for 2 minutes
          holdFor(2 minutes),
          jumpToRps(75),                 // just to 75 request per second in 2 minutes and hold for 2 minutes
          holdFor(2 minutes),
          jumpToRps(100),
          holdFor(2 minutes),               // jump to 100 request per second in 2 minutes and hold for 2 minutes
          jumpToRps(75),
          holdFor (60 seconds),
          jumpToRps(50),
          holdFor(60 seconds),
          jumpToRps(25),
          holdFor(60 seconds),
          jumpToRps(1),
          holdFor(2 minutes)

    ).protocols(GlobalVar.httpConf)

  }