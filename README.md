[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/5vSwSRXp)
[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=22172636)
<p><strong>Description:</strong> You will use the Queue interface and the Stack class provided in the Java API to process through a large set of data and organize the items being inputted.<br /><strong></strong></p>
<p><strong>Directions: </strong>Read ALL of the following directions before beginning your pseudocode.&nbsp; There will be TWO grades - Pseudocode and Code.</p>
<p>1. Create an algorithm/pseudocode for each of the java files (Train.java and MyProgram.java) in this program. Keep in mind that the program should outline where loops and if statements will appear, you will also outline what methods you are writing - include modifiers, return types, and parameters along with code for the method.</p>
<p><br />2. Use the description below to write your pseudocode.&nbsp; You will be given 3 files - Train.java, MyProgram.java, and data.txt.&nbsp; You will also be given the code to read from the text file.</p>
<p>You have been tasked with processing a set of text input, the data for all the train cars that are entering a train station.&nbsp; Each of the train cars will have the following information:</p>
<ul>
    <li>Car Name/Number - will always be in the format "CAR#####"</li>
    <li>Contents</li>
    <li>Origin City</li>
    <li>Destination City</li>
    <li>Weight</li>
    <li>Miles Traveled</li>
</ul>
<p>The file will end with the text "END"</p>
<p>As cars enter the train station, it is your responsibility to create an instance of a Train (you will write the Train class).&nbsp;</p>
<ul>
    <li>AP Java - Unit 2 Notes on Classes = <a class="inline_disabled" href="https://docs.google.com/presentation/d/15cwDeaHsdKHTCFDozNhkXPqpIQib9JERErO1X95UG5M/edit?usp=sharing" target="_blank" rel="noopener">https://docs.google.com/presentation/d/15cwDeaHsdKHTCFDozNhkXPqpIQib9JERErO1X95UG5M/edit?usp=sharing</a></li>
</ul>
<p>You must read in ALL input before you can process the cars.&nbsp; When processing the cars, you will use the following information/rules:</p>
<ul>
    <li><span>The main line is track 0 - this is where you input will all be stored initially - think about what type of data structure should be used for this.</span></li>
    <li><span>Cars that have over 700 miles are put on track 1 for inspection, destination does not matter.&nbsp; After all cars have been initially processed, you will inspect cars and then add them to trains that have room within the weight limit remaining.</span></li>
    <li><span>There are 4 more tracks, cars are moved to these tracks using the following rules as long as there is room within the weight limit of that track (this will be defined for you in the file you're given - "limitTrackA", "limitTrackB", and "limitTrackC").  The fourth track, Track D has no limit.</span>
        <ul>
            <li><span>Track A contains only cars destined for Trenton. </span></li>
            <li><span>Track B will contain cars destined for Charlotte.</span></li>
            <li><span>Track C will contain cars destined for Baltimore. </span></li>
            <li><span>Track D will contain cars that are going to other destinations or that cannot be delivered due to weight limits on that track.</span></li>
        </ul>
    </li>
</ul>
<p><span><em><strong>SUBMIT plan to your teacher using the document for this assignment.</strong></em></span></p>
