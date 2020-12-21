# Additional requirements clarifications

Quoting:
<blockquote>

```text
Hi Atanas,

I am passing on the message:


Looks great as a breakdown.

Some notes & hints:

- Performance
            - `TODO` research if custom thread pool is needed for vertical performance scalability. Vertical performance is not really important in this case, so i would concentrate on the horizontal scalability :)


- Initial idea - `backoff and retry logic`.
        - `TODO` research if `backoff and retry logic` is the best way.Looks good as a strategy to solve that problem, so additional research in other approaches would be really low priority :)


- Outbound (Sender/Forwarder):
                - This will be an Interface which can have multiple implementations which can be used to either forward/send a notification to a specific channel.
                    - In the future this can be extended to push notifications etc.
                    - For the purposes of the scope of this task this will have three implementations - email, sms and slack. Feel free to implement just the email sending capabilities :)

```

</blockquote>
