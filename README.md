# LoadAndLockAppInstances 
Use the **ServerListenerLoadAndLockAppInstances** server listener for [Wowza Streaming Engine™ media server software](https://www.wowza.com/products/streaming-engine) when you need to have an application load and remain loaded until the Wowza Streaming Engine media server is shut down. The server listener can be configured to load multiple applications or application instances on the server.

This repo includes a [compiled version](/lib/wse-plugin-loadandlockappinstances.jar).

## Prerequisites
Wowza Streaming Engine 4.0.0 or later is required.

## Usage
Specify the applications and application instances that you want to load for each virtual host by leveraging the appropriate properties.

## More resources
To use the compiled version of this module, see [Load and lock an appinstance with a Wowza Streaming Engine server listener](https://www.wowza.com/docs/how-to-use-a-server-listener-to-load-and-lock-an-appinstance-serverlistenerloadandlockappinstances).

[Wowza Streaming Engine Server-Side API Reference](https://www.wowza.com/resources/serverapi/)

[How to extend Wowza Streaming Engine using the Wowza IDE](https://www.wowza.com/docs/how-to-extend-wowza-streaming-engine-using-the-wowza-ide)

Wowza Media Systems™ provides developers with a platform to create streaming applications and solutions. See [Wowza Developer Tools](https://www.wowza.com/resources/developers) to learn more about our APIs and SDK.

## Contact
[Wowza Media Systems, LLC](https://www.wowza.com/contact)

## License
This code is distributed under the [Wowza Public License](/LICENSE.txt).
