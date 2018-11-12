
/**几种简单的排序算法**/
/* 交换 */
Array.prototype.swap = function (i, j) {
	var temp = this[i];
	this[i] = this[j];
	this[j] = temp;
};
/* 冒泡排序 */
Array.prototype.bubbleSort = function () {
	for (var i = this.length - 1; i > 0; --i) {
		for (var j = 0; j < i; ++j) {
			if (this[j] > this[j + 1]) {
				this.swap(j, j + 1);
			}
		}
	}
};
/* 选择排序 */
Array.prototype.selectionSort = function () {
	for (var i = 0; i < this.length; ++i) {
		var index = i;
		for (var j = i + 1; j < this.length; ++j) {
			if (this[j] < this[index]) {
				index = j;
			}
		}
		this.swap(i, index);
	}
};
/* 插值排序 */
Array.prototype.insertionSort = function () {
	for (var i = 1; i < this.length; ++i) {
		var j = i, value = this[i];
		while (j > 0 && this[j - 1] > value) {
			this[j] = this[j - 1];
			--j;
		}
		this[j] = value;
	}
};
/* 希尔排序 */
Array.prototype.shellSort = function () {
	for (var step = this.length >> 1; step > 0; step >>= 1) {
		for (var i = 0; i < step; ++i) {
			for (var j = i + step; j < this.length; j += step) {
				var k = j, value = this[j];
				while (k >= step && this[k - step] > value) {
					this[k] = this[k - step];
					k -= step;
				}
				this[k] = value;
			}
		}
	}
};
/* 快速排序 */
Array.prototype.quickSort = function (s, e) {
	if (s == null) {
		s = 0;
	}
	if (e == null) {
		e = this.length - 1;
	}
	if (s >= e) {
		return;
	}
	this.swap((s + e) >> 1, e);
	var index = s - 1;
	for (var i = s; i <= e; ++i) {
		if (this[i] <= this[e]) {
			this.swap(i, ++index);
		}
	}
	this.quickSort(s, index - 1);
	this.quickSort(index + 1, e);
};
/* 快速堆栈排序 */
Array.prototype.stackQuickSort = function () {
	var stack = [0, this.length - 1];
	while (stack.length > 0) {
		var e = stack.pop(), s = stack.pop();
		if (s >= e) {
			continue;
		}
		this.swap((s + e) >> 1, e);
		var index = s - 1;
		for (var i = s; i <= e; ++i) {
			if (this[i] <= this[e]) {
				this.swap(i, ++index);
			}
		}
		stack.push(s, index - 1, index + 1, e);
	}
};
/* 归并排序排序 */
Array.prototype.mergeSort = function (s, e, b) {
	if (s == null) {
		s = 0;
	}
	if (e == null) {
		e = this.length - 1;
	}
	if (b == null) {
		b = new Array(this.length);
	}
	if (s >= e) {
		return;
	}
	var m = (s + e) >> 1;
	this.mergeSort(s, m, b);
	this.mergeSort(m + 1, e, b);
	for (var i = s, j = s, k = m + 1; i <= e; ++i) {
		b[i] = this[(k > e || j <= m && this[j] < this[k]) ? j++ : k++];
	}
	for (var i = s; i <= e; ++i) {
		this[i] = b[i];
	}
};
/* 堆排序 */
Array.prototype.heapSort = function () {
	for (var i = 1; i < this.length; ++i) {
		for (var j = i, k = (j - 1) >> 1; k >= 0; j = k, k = (k - 1) >> 1) {
			if (this[k] >= this[j]) {
				break;
			}
			this.swap(j, k);
		}
	}
	for (var i = this.length - 1; i > 0; --i) {
		this.swap(0, i);
		for (var j = 0, k = (j + 1) << 1; k <= i; j = k, k = (k + 1) << 1) {
			if (k == i || this[k] < this[k - 1]) {
				--k;
			}
			if (this[k] <= this[j]) {
				break;
			}
			this.swap(j, k);
		}
	}
};

/* 求字符串的unicode码 */
function unicode(str) {
	var uni = 0;
	for (var i = 0; i < str.length; i++) {
		uni += str.charCodeAt(i) / 6553.5 * Math.pow(10, str.length - i);
	}
	return uni;
}

/*
 *示例代码 
 *type bubble|selection|insertion|quick|statcquick|merge|heap
 */
function exampleSort(type) {
	var array = new Array();
	//实例化array .....
	eval("array." + type + "Sort()");
}

