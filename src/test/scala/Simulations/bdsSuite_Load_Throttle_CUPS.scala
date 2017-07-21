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
1. Start scenario  RAMPING UP
2. Do nothing for 10 seconds
3. Inject 25 users per second at a constant rate of 2 minutes
4. Do nothing for 10 seconds
5. Inject 50 users per second at a constant rate of 2 minutes
6. Cool off for 10 seconds
7. Inject 75 users per second at a constant rate of 2 minutes
8. Cool off for 10 seconds
9. Inject 100 users per second at a constant rate of 2 minutes
10. Cool off for 10 seconds
11. Inject 150 users per second at a constant rate of 2 minutes
12. Cool off for 10 seconds
13. Inject 200 users per second at a constant rate of 2 minutes
14. Cool off for 10 seconds
15. Inject 250 users per second at a constant rate of 2 minutes
16. Cool off for 10 seconds
17. Inject 300 users per second at a constant rate of 2 minutes
18. Cool off for 10 seconds
19. Inject 350 users per second at a constant rate of 2 minutes
20. Cool off for 10 seconds
21. Inject 450 users per second at a constant rate of 2 minutes
22. Cool off for 10 seconds
23. Inject 500 users per second at a constant rate of 2 minutes
22. Cool off for 10 seconds
23. Start scenario  RAMPING DOWN
24. RampDown volume to 300, 200, 100, 50, 25  users per second at a constant rate of 2 minutes
with a 10 second cool down between each volume
25. Throttle down to 25 request per second and hold for 60 seconds
 */

  class bdsSuite_Load_Throttle_CUPS extends Simulation
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
          nothingFor(10 seconds),
          constantUsersPerSec(50) during(2 minutes),
          nothingFor(10 seconds),
          constantUsersPerSec(75) during(2 minutes),
          nothingFor(10 seconds),
          constantUsersPerSec(100) during(2 minutes),
          nothingFor(10 seconds),
          constantUsersPerSec(150) during(2 minutes),
          nothingFor(10 seconds),
          constantUsersPerSec(200) during(2 minutes),
          nothingFor(10 seconds),
          constantUsersPerSec(250) during(2 minutes),
          nothingFor(10 seconds),
          constantUsersPerSec(350) during(2 minutes),
          nothingFor(10 seconds),
          constantUsersPerSec(450) during(2 minutes),
          nothingFor(10 seconds),
          constantUsersPerSec(500) during(2 minutes),
          nothingFor(10 seconds),
          constantUsersPerSec(300) during(2 minutes),
          nothingFor(10 seconds),
          constantUsersPerSec(200) during(2 minutes),
          nothingFor(10 seconds),
          constantUsersPerSec(100) during(2 minutes),
          nothingFor(10 seconds),
          constantUsersPerSec(50) during(2 minutes),
          nothingFor(10 seconds),
          constantUsersPerSec(25) during(2 minutes)
        )).throttle(       // inject 30 users per second for 2 minute
          reachRps(25) in (2 minutes),  // inject 25 request per second in 2 minutes and hold for 2 minutes
          holdFor(60 seconds)
    ).protocols(GlobalVar.httpConf)

  }