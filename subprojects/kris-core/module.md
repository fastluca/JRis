# Module kris-core

KRis core module, containing the domain logic on how to interpret RIS records and RIS files.

This module can be used in case you already have the flows or collections of Strings and handle
reading from or writing to files yourself.

Relate to the module `kris-io` to find add-on functionality that takes care of the IO tasks
like writing to file or reading to file.

# Package ch.difty.kris

`KRis` is the object at the core of the KRis library. If offers a number of accessor methods
to Java Clients that offer to convert `String`s to `RisRecord`s or vice versa, in different
flavors of data input, non-blockingly using kotlin flows or RxJava observables or in a blocking
manner accepting lists.

`KrisExtensions` offers a number of handy extension functions to kotlin clients.

# Package ch.difty.kris.domain

Home of the `RisRecord`, the internal representation of a RIS data record.
Also contains the `RisType`, in identifier for the various types of `RisRecords`,
and the `RisTag`, an enum that captures all the relevant attributes of the individual
tags that must or may appear in a `RisRecord`.

# Package ch.difty.kris.implementation

This internal package bundles the core business logic on how to handle content in RIS format.
In this module, the code does not care about the source or target of text lines.
Instead, it simply converts between flows of `String`s and flows of `RisRecord`.

* `RisExport` cares about transforming flows of `RisRecord`s into flows of `String`s.
* `RisImport` cares about transforming flows of `String`s into flows of `RisRecord`s.