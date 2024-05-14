**jrosmoveit** - Java module which allows to interact with [MoveIt](https://moveit.ros.org/) in ROS. It allows to set target pose for the robot, plan trajectory and execute it. With **jrosmoveit** you can interact with [MoveIt](https://moveit.ros.org/) from Java directly and manipulate variety of robotic arms.

It contains only interfaces and classes which are agnostic to version of ROS. Its implementations available separately:

For ROS2 see [jros2moveit](https://github.com/pinorobotics/jros2moveit)

For ROS1 see [jros1moveit](https://github.com/pinorobotics/jros1moveit)

# Requirements

Java 17+

# Download

[Release versions](jrosmoveit/release/CHANGELOG.md)

Or you can add dependency to it as follows:

Gradle:

```
dependencies {
  implementation 'io.github.pinorobotics:jrosmoveit:2.0'
}
```

# Documentation

[Documentation](http://pinoweb.freetzi.com/jrosmoveit)

[Development](DEVELOPMENT.md)

# Contacts

aeon_flux <aeon_flux@eclipso.ch>
