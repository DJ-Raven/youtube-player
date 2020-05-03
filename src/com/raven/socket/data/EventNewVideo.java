package com.raven.socket.data;

import com.raven.models.Video;

public interface EventNewVideo {

    public void execute(Video video);
}
