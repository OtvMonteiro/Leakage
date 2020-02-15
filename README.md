`Version: 1.1`
# Leakage
  A Minecraft Bukkit Plugin that creates a spawner near a player, assigned or randomly.
---
![Screenshot](https://github.com/OtvMonteiro/Leakage/blob/master/Images/screenshot%20(4).webp)

>  **Hell is leaking, and you get a taste of it**
_ _ _ 

## About
  Creates a MobSpawner with a random Entity, surrounded by netherrack, that arrives with an explosion!
  It uses random values, within a configurable boundary, to spawn near a player that issued the /leak command or was chosen (by either random chance or the OP).

![RandomAssignment](https://github.com/OtvMonteiro/Leakage/blob/master/Images/screenshot%20(5).webp)

### Commands
  #### leak:
    description: Creates a "monster leak" near the player
    usage: Type /leak to use
  #### upperbound:
    description: Changes the upperbound to place random "monster leak" near player, standard is 10
    usage: Type /upperbound [number] , where [number] is the new value for maximum leakage distance (X and Z)
  #### leakProbability:
    description: Changes the probability of natural occuring leakages, standard is 1 in 60
    usage: Type /leakProbability [number]  Probability is set as 1 in [number]
  #### schedulerTime:
    description: Set time till script that checks if a random leak occurs, standard is 1 minute
    usage: Type /schedulerTime [number] to change time till next check
  
### Ambience

  Changes the Wheater to a Thunderstorm and spawns dozens of mobs!
  ![RandomAssignment](https://github.com/OtvMonteiro/Leakage/blob/master/Images/screenshot%20(6).webp)

 _ _ _

## To do
* Add a permission type for op to use /leak [user] (console already implemented)
* When issuing /leak as a player, copy from the console method how to select a different player
