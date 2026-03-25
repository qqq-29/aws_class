import os
import glob

sub_folders = [folder for folder in os.listdir(os.path.join('day13(ml)', 'images')) if os.path.isdir(os.path.join('day13(ml)', 'images', folder))]
print(sub_folders)
a, b = [], []
for f in sub_folders:
	file_path = os.path.join('day13(ml)', 'images', f, '*.*')
	for file in glob.glob(file_path):
		a.append(file)
	# images.append(file_path)

print(a)