import {
  Box,
  Card,
  Grid,
  TextField,
  Typography,
  InputAdornment,
  Button,
} from "@mui/material";
import axios from "axios";
import React, { useEffect, useState } from "react";
import SearchIcon from "@mui/icons-material/Search";
import { Link } from "react-router-dom";

const Feed = () => {

  //gestiamo la ricerca
  const [query, setQuery] = useState('');

  //gestiamo lo stato del post
  const [post, setPost] = useState([]);

    // Fetch data based on search query
    useEffect(() => {
        const fetchPosts = async () => {
            try {
                const response = await axios.get(
                    `http://localhost:8080/rest-api/posts/${query}`
                );
                setPost(response.data);
            } catch (error) {
                console.error("Error fetching posts:", error);
            }
        };

        const fetchInitialPosts = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/rest-api/posts/all`);
                setPost(response.data);
            } catch (error) {
                console.error("Error fetching initial posts:", error);
            }
        };

        if (query.length === 0) {
            fetchInitialPosts();
        } else if (query.length > 2) {
            fetchPosts();
        }
    }, [query]);

console.log(post);
  return (
    <Grid container spacing={2} sx={{ margin: "2%" }}>
      <Grid item xs={12} sx={12} md={12} lg={12}>
      <Button sx={{ margin: "1% 2%" }} variant="outlined">
            <Link to="/">Home</Link>
          </Button>
        <Box>
          <TextField
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">
                  <SearchIcon />
                </InputAdornment>
              ),
            }}
            placeholder="Search..."
            sx={{ width: "75%", padding: "2% auto" }}
            fullWidth
            onChange={(e) => setQuery(e.target.value)}
          />
        </Box>
      </Grid>
      {post &&
        post.map((postElem) => {
          return (
            <Grid key={postElem.id} item xs={12} md={6} lg={4}>
              <Card sx={{ padding: "3%", overflow: "hidden", width: "84%"}}>
                <Typography
                  variant="h5"
                  sx={{ fontSize: "2rem", fontWeight: "600" }}
                >
             {postElem.profile}
                </Typography>
                <Typography sx={{ color: "#585858", marginTop:"2%" }} variant="body" >
                  Description: {postElem.desc}
                </Typography>
                <br />
                <br />
                <Typography variant="h6">
                  Years of Experience: {postElem.exp} years
                </Typography>

                <Typography gutterBottom  variant="body">Skills : </Typography>
                {postElem.techs.map((s, i) => {
                  return (
                    <Typography variant="body" gutterBottom key={i}>
                      {s} .
                      {``}
                    </Typography>
                  );
                })}
  
              </Card>
            </Grid>
          );
        })}
    </Grid>
  );
};

export default Feed;
