package com.kraatz.player.service

import androidx.media3.session.MediaSessionService

class SimpleMusicService : MediaSessionService() {
    
    override fun onGetSession(controllerInfo: androidx.media3.session.MediaSession.ControllerInfo): androidx.media3.session.MediaSession? {
        // Return null for now - will implement later
        return null
    }
}
