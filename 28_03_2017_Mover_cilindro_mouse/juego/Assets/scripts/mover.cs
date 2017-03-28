using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class mover : MonoBehaviour {

    private Transform _start;
    private Transform _target;
    public bool _hasArrive;
	public float _speed;
	public float _distanceToArrive;
	public Transform[] _path;
    private int positionList = 0;

	// Use this for initialization
	void Start () {
		begin ();
	}
	
	// Update is called once per frame
	void Update () {
		_hasArrive = HasArrive();
		if (!_hasArrive)
			move ();
		else
			Arrive ();
		
	}
	private void Arrive(){        
		swaptarget();
		begin ();        
	}

    private void swaptarget() {
        int limit = _path.Length;
        if (positionList < limit)
        {
            _start = _path[positionList];
            positionList++;
            _target = _path[positionList];
        }
        else {
            positionList = 0;                       
        }
          
	}

	public bool HasArrive(){
		if((transform.position - _target.position).magnitude <= _distanceToArrive)
			return true;
		return false;
	}

	private void begin(){
        _start = _path[positionList];
        _target = _path[positionList + 1];        
        transform.position = _start.position;
		_hasArrive = false;
       
	}

	public void move(){
		Vector3 direction = _target.position - this.transform.position;
		Vector3 step = direction.normalized * _speed * Time.deltaTime;
		this.transform.position += step;
	}
}
