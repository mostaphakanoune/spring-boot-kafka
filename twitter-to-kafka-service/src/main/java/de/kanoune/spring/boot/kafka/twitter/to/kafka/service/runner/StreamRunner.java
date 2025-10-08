package de.kanoune.spring.boot.kafka.twitter.to.kafka.service.runner;

import twitter4j.TwitterException;

public interface StreamRunner {

    void run() throws TwitterException;
}
