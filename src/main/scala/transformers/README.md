The code in the LoggingEither object of Example.scala demonstrates a
monad stack of Writer[List[String], \/[String, A]]

This is an immensely useful stack for functional logging. The disjunction
is used for error handling and failure reporting, the writer is used for
accumulating log data through a series of operations.

Note that thanks to the transformer we are able to use a single level of
for-comprehension to cut through both the writer and the disjunction.

Also note that writer requires a semigroup of the type used to accumulate
(in this case List[String]) which is the reason for importing scalaz.std.list._

Handling logging in this way has some concrete benefits

https://github.com/localytics/marketing-delivery/blob/master/app/com/localytics/delivery/inbox/CampaignParsing.scala#L180

Localized accumulation of data (no need to build comprehensive failure messages
from multiple call sites)

Standard log format means trivial plugin to logging/monitoring systems

https://github.com/localytics/marketing-delivery/blob/master/app/com/localytics/delivery/Logging.scala#L49

https://metrics.librato.com/s/spaces/108399

https://github.com/localytics/logged-either-dropwizard-connector
