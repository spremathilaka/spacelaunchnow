package com.zotiko.spacelaunchnow.ui.data

import com.zotiko.spacelaunchnow.R
import com.zotiko.spacelaunchnow.ui.util.ResText
import com.zotiko.spacelaunchnow.ui.util.Text

enum class PageErrorState(val message: Text) {
    NO_NETWORK(ResText(R.string.no_internet_error_msg)),
    SERVER_ERROR(ResText(R.string.generic_server_error)),
}