package Simulations

import EndPoints.corecheck
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import Common._
import scala.concurrent.duration._

/*
RUN TIME: 6 minutes
STORY LINE:
1. Start scenario
2. Do nothing for 10 seconds
3. Inject 25 users per second at a constant rate of 2 minutes
4. Inject rampdown to 1 request per second for 2 minutes
5. Inject rampup to 25 request per second for 2 minutes
 */

class bdsSuite_Baseline_25_RPS_CUPS extends Simulation
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
      nothingFor(10 seconds),
      constantUsersPerSec(25) during(2 minutes),
    )).throttle(
    reachRps(1) in (2 minutes),
    reachRps(25) in (2 minutes),
    holdFor(2 minutes)
  ).protocols(GlobalVar.httpConf)

}