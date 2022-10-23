Tests for **jrosmoveit** library.

Integration tests rely on [Panda robot](https://github.com/ros-planning/panda_moveit_config) and TF2 Buffer Server.

Install Panda:

``` bash
apt install ros-noetic-panda-moveit-config
```

Use following command to run it:

``` bash
roslaunch panda_moveit_config demo.launch
```

If it fails apply fix from [here](https://answers.ros.org/question/384900/failed-to-lunch-this-command/)

After that you need start TF2 Buffer Server:

``` bash
rosrun tf2_ros buffer_server
```

