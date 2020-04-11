# Module kris-io

KRis extra module that builds on top of `kris-core` and offers additional functionality
to read from files or write to files in various ways. If your application manages that
autonomously, you don't need the IO module, e.g. in the context of a web application that
does not have access to the file system directly and needs to interact with the Browser
for uploading/downloading content to the user's machine.

# Package ch.difty.kris

* `KRisIO` can be used from Java or Kotlin to work with exiting readers, files or streams.
* `ExportExtensions` contains extension functions to the kotlin client for exporting `RisRecord`s.
* `ImportExtensions` contains extension functions to the kotlin client for importing `RisRecord`s.