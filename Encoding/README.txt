COMP 550 - HW5b 
Tye Zasacky
Collaborators: None

Syllabus Encodings: 
	Length of original string: 8812 characters
	Fixed Length: Length of encoded string: 70496
	Huffman: (assignment.txt frequencies): Length of encoded string: 42601
	Huffman: (syllabus.txt frequencies): Length of encoded string: 42083
	Best: The syllabus frequencies. The other Huffman scheme is very similar.
		(likely because english always has similar frequencies given large
		enough sample) 
		Saves about 40% over fixed-length.
	Worst: Fixed-length

Random.txt Encodings:
	Length of original string: 1090 characters
	Fixed Length: Length of encoded string: 8720
	Huffman (using random.txt frequencies): Length of encoded string: 6721
	
	Conclusion: Huffman encoding is still better, but only by a little over 20%.
		Still good savings, but significantly worse than with non-random. 
		Because of randomness, likely that space improvement would not continue
		with larger sample. 

Overall conclusion: 
	Huffman encoding saves significant space with predictable character frequencies.
	However, it is not serializable, which doesn't allow for parallel processing.
	 