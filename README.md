BigTop Bazaar
=============
A simulator for customer movement at conference.  The ultimate goal is to simulate the positions of customers over time for a conference room floor consisting of a number of boths.

Model Overview
==============
The customers are modeled as particles and the booths as static point charges.  The interaction strength between each particle and each booth is determined by the interest of that customer in that booth.  The interaction strengths are determined by a randomly-parameterized factor analysis model with a user-defineable number of latent factors.  The randomly-generated latent factors and customer weights are multiplied to generate a matrix of customer-booth interaction strengths.  Langevin dynamics are used to simulate the movement of the customers.

TODO
====
* Booth layout generator
* Evaluate need for switch function with Gaussian
* Latent factor / weights model
* Seed CLI parameter
* Steps CLI parameter
* Figure out units (using OpenMM units -- need to verify)
* Add more unit tests
* Rename types package to base
* Move SBC to its own file in potentials
* Overdamp boundaries? (i.e., switching LL?)
* Default configuration values

