java-spellchecker
=================

Current status: Complete. No longer being developed. 

Return a list of suggestions for a misspelled word

-Version 0.3  

````Breaking out suggest1,2 and 3 methods to accept LinkedList<String> corrections and a wrong word.````

-Version 0.2

````Breaking out the correct method to Omission, Swap, Single Letter and Add a letter.````
````Adding the +All methods.````

-Version 0.1

````This is the initial build and design of the Suggest class.````


Getting Started
===============
1. ````javac SpellCheck.java````
2. ````java SpellCheck ````

Methodology
===========
I created a data structure ABCTrie for the use in this program. It is closely related to a Trie but specialized to deal with words and perform functions on itself, a dictionary. 
