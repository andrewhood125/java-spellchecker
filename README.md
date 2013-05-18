java-spellchecker
=================

Return a list of suggestions for a misspelled word
  Version 0.3
  - Breaking out suggest1,2 and 3 methods to accept LinkedList<String> corrections
    and a wrong word.
  
  Version 0.2
  - Breaking out the correct method to Omission, Swap, Single Letter and Add a letter.
  - Adding the +All methods.
    Before +All methods
       (tommorow) 1*TOMM* 2*TOMMER* 3** 
       (Carribean) 1*CARRIE* 2*CARRIABLE* 3** 
       (reicieve) 1*REIC* 2*REICHENB* 3** 
       (basicly) 1*BASICY* 2*BASICAL* 3** 
       (bizzare) 1*BIZZ* 2*BIZZ* 3** 
    After +All methods
       (tommorow) *tomorrow* *tomorrow* *tomorrow* *tomorrow* *tomorrow* 1*TOMM* 2*TOMMER* 3** 
       (Carribean) *Caridean* *Caribbean* *Caribbean* *Caridean* *Caribbean* *Caribbean* *Caribbean* 1*CARRIE* 2*CARRIABLE* 3** 
       (reicieve) *receive* *relieve* *relieve* *receive* 1*REIC* 2*REICHENB* 3** 
       (basicly) *bailly* *basely* *easily* *busily* *basely* *basify* *basils* *basify* *basics* *basial* *basics* 1*BASICY* 2*BASICAL* 3** 
       (bizzare) *izzard* *bizarre* *bizarre* *bizarre* *bizarre* *rizzar* *bizarre* 1*BIZZ* 2*BIZZ* 3** 
  Version 0.1 
  -This is the initial build and design of the Suggest class


Getting Started
===============
1. ````javac SpellCheck.java````
2. ````java SpellCheck ````

Methodology
===========
I created a data structure ABCTrie for the use in this program. It is closely related to a Trie but specialized to deal with words and perform functions on itself, a dictionary. 
