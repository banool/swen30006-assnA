import os
import sys

seeds = set()
for i in os.listdir():
    if i == sys.argv[0]:
        continue
    seeds.add(i.split('_')[0])

better = 0
worse = 0
for i in seeds:
    with open(i + '_mine.txt', 'r') as f:
        score1 = float(f.read().split()[6])
    with open(i + '_sample.txt', 'r') as f:
        score2 = float(f.read().split()[6])
    print(f'{i: <8}', end='')
    if score1 < score2:
        better += 1
        print('Better')
    else:
        worse += 1
        print('Worse')

print()
print('Num worse: ' + str(worse))
print('Num better: ' + str(better))
