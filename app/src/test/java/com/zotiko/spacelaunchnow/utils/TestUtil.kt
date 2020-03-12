package com.zotiko.spacelaunchnow.utils

import com.zotiko.spacelaunchnow.model.LaunchEvent
import com.zotiko.spacelaunchnow.model.Mission

class TestUtil {
    companion object {

        fun getDummyLaunchEvent(): LaunchEvent {
            return LaunchEvent(
                name = "Falcon 9 Block 5 | Starlink 5",
                imgUrl = null,
                mission = getDummyMission()
            )
        }

        private fun getDummyMission(): Mission {
            return Mission(
                description = "Falcon 9 booster B1048 is a",
                type = "Communications",
                name = "Starlink 5"
            )
        }
    }
}