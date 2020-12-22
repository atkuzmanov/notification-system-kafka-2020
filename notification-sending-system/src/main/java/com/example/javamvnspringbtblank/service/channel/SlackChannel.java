package com.example.javamvnspringbtblank.service.channel;

/**
 * A class which implements the `com.example.javamvnspringbtblank.service.channel.Channel` interface.
 * As per [Additional requirements clarifications](#additional-requirements-clarifications) (see README.md) it is not
 * required and therefore does not provide its own implementations of the methods required by the interface contract,
 * so it inherits the interface's default methods which throw
 * `com.example.javamvnspringbtblank.exception.NotificationException`, which is in turn handled by
 * `com.example.javamvnspringbtblank.exception.RestResponseEntityExceptionHandler`.
 */
public class SlackChannel implements Channel {
}
