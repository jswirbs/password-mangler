# password-mangler

Simple password mangler written to crack passwords for a pentest on a client.
There were a few recognizable patterns in a couple of passwords I obtained through
phishing and general OSINT, so I created this to crack other employees passwords
assuming some would also follow a similar pattern of a word surrounded by numbers
and one or two symbols. This helped create password lists that were used to 
successfully crack additional credentials.

Takes in a word, a max number to use, and as many independent symbols as you want
and then outputs all possible permutations to a txt file.
