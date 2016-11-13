find_solution(State,Moves) :- solve(State,[], Moves).

solve(State,_,[]) :- goal_state(State).

solve(State,PreviousStates,[FirstMove | RestMoves]) :-
 acceptable_move(State, PreviousStates, FirstMove, NewState),
 solve(NewState, [State|PreviousStates], RestMoves).

acceptable_move(State, PreviousStates, Move, NewState) :-
 find_move(State, Move),
 apply_move(State, Move, NewState),
 new_state(NewState, PreviousStates).

goal_state([1/2/3/8/0/4/7/6/5]).

new_state(S,P) :-
        not(member(S,P)).
%Puzzle Moves


find_move(State,left):-
                       left(State).
                       
find_move(State,right):-
                        right(State).

find_move(State,up):-
                     up(State).
                     
find_move(State,down):-
                       down(State).

left([A/0/C/D/E/F/G/H/I]).
left([A/B/0/D/E/F/G/H/I]).
left([A/B/C/D/0/F/G/H/I]).
left([A/B/C/D/E/0/G/H/I]).
left([A/B/C/D/E/F/G/0/I]).
left([A/B/C/D/E/F/G/H/0]).


up([A/B/C/0/E/F/G/H/I]).
up([A/B/C/D/0/F/G/H/I]).
up([A/B/C/D/E/0/G/H/I]).
up([A/B/C/D/E/F/0/H/I]).
up([A/B/C/D/E/F/G/0/I]).
up([A/B/C/D/E/F/G/H/0]).

right([0/B/C/D/E/F/G/H/I]).
right([A/0/C/D/E/F/G/H/I]).
right([A/B/C/0/E/F/G/H/I]).
right([A/B/C/D/0/F/G/H/I]).
right([A/B/C/D/E/F/0/H/I]).
right([A/B/C/D/E/F/G/0/I]).

down([0/B/C/D/E/F/G/H/I]).
down([A/0/C/D/E/F/G/H/I]).
down([A/B/0/D/E/F/G/H/I]).
down([A/B/C/0/E/F/G/H/I]).
down([A/B/C/D/0/F/G/H/I]).
down([A/B/C/D/E/0/G/H/I]).


apply_move([A/0/C/D/E/F/G/H/I],left,[0/A/C/D/E/F/G/H/I]).
apply_move([A/B/0/D/E/F/G/H/I],left,[A/0/B/D/E/F/G/H/I]).
apply_move([A/B/C/D/0/F/G/H/I],left,[A/B/C/0/D/F/G/H/I]).
apply_move([A/B/C/D/E/0/G/H/I],left,[A/B/C/D/0/E/G/H/I]).
apply_move([A/B/C/D/E/F/G/0/I],left,[A/B/C/D/E/F/0/G/I]).
apply_move([A/B/C/D/E/F/G/H/0],left,[A/B/C/D/E/F/G/0/H]).

apply_move([A/B/C/0/E/F/G/H/I],up,[0/B/C/A/E/F/G/H/I]).
apply_move([A/B/C/D/0/F/G/H/I],up,[A/0/C/D/B/F/G/H/I]).
apply_move([A/B/C/D/E/0/G/H/I],up,[A/B/0/D/E/C/G/H/I]).
apply_move([A/B/C/D/E/F/0/H/I],up,[A/B/C/0/E/F/D/H/I]).
apply_move([A/B/C/D/E/F/G/0/I],up,[A/B/C/D/0/F/G/E/I]).
apply_move([A/B/C/D/E/F/G/H/0],up,[A/B/C/D/E/0/G/H/F]).

apply_move([0/B/C/D/E/F/G/H/I],right,[B/0/C/D/E/F/G/H/I]).
apply_move([A/0/C/D/E/F/G/H/I],right,[A/C/0/D/E/F/G/H/I]).
apply_move([A/B/C/0/E/F/G/H/I],right,[A/B/C/E/0/F/G/H/I]).
apply_move([A/B/C/D/0/F/G/H/I],right,[A/B/C/D/F/0/G/H/I]).
apply_move([A/B/C/D/E/F/0/H/I],right,[ A/B/C/D/E/F/H/0/I]).
apply_move([A/B/C/D/E/F/G/0/I],right,[A/B/C/D/E/F/G/I/0]).

apply_move([0/B/C/D/E/F/G/H/I],down,[D/B/C/0/E/F/G/H/I]).
apply_move([A/0/C/D/E/F/G/H/I],down,[A/E/C/D/0/F/G/H/I]).
apply_move([A/B/0/D/E/F/G/H/I],down,[A/B/F/D/E/0/G/H/I]).
apply_move([A/B/C/0/E/F/G/H/I],down,[A/B/C/G/E/F/0/H/I]).
apply_move([A/B/C/D/0/F/G/H/I],down,[A/B/C/D/H/F/G/0/I]).
apply_move([A/B/C/D/E/0/G/H/I],down,[A/B/C/D/E/I/G/H/0]).





















