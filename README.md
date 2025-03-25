# All you need is Java

Sample application with a backend in Java, a frontend in Java and being displayed in a [Webview](https://github.com/webview/webview).

Because ***all you need is Java***.

## What?

This is not exactly a template of "how to make a full java project", but it can be used as a starting point of such.

There's two subprojects in this repository, `ui` and `app`.
- All HTML, CSS and frontend Java code is in `ui`.
- All HTTP server and Webview code is in `app`, which is also where the project is fully built.

The UI runs on TeaVM, which compiles Java to JavaScript. Additionally, Tailwind CSS is used for styling. (Although it's [broken on Windows](https://github.com/tailwindlabs/tailwindcss/discussions/17371))

The App uses [Armeria](https://github.com/line/armeria), a reasonably featureful HTTP server, and [Webview Java](https://github.com/webview/webview_java), in a fashion similar to how Tauri and Wails works.

Also, ShadowJar is preconfigured in the project to build a jar with all batteries included.

## Why?

Because it was actually a very fun project to make!

Also, it's my way of getting this idea out into the world.

Don't like Armeria? Change it for Javalin!
Java in the front-end is crazy? I agree, setup a Gatsby frontend with React! Heck, you barely don't even need the backend.
Just serve the frontend from a static file server and bind Java-JS functions using Webview's `bind`.

The only "glue" you need to do is point Webview to the correct port and serve the frontend from there.

I wanted to make this project because sometimes I much rather work with Java in the backend than Node.js, but I'd
still like to have a desktop application to show me what's happening on my application instead of a terminal.